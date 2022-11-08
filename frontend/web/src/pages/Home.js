import  React, {useEffect, useState } from 'react'
import SingleProduct from '../components/SingleProduct'
import "../components/style.css";
import Filter from '../components/Filter';
import {FilterState } from '../context/FilterContext'
import DropdownMenu from 'react-bootstrap/esm/DropdownMenu';
import axios from 'axios';
import Pagination from '../components/Pagination';
import { Dropdown } from 'react-bootstrap';


export default function Home () {
  const [currentPage,setCurrentPage]=useState(1)
  const [itemsPerPage,setItemsPerPage] = useState(10)
  const lastPostIndex = currentPage * itemsPerPage;
  const firstPostIndex = lastPostIndex - itemsPerPage;
  const [products, setProducts] = useState([]);
  
  const {
    state: {sort,byCate1,byCate2,byCate3,byCate4,searchQuery }
  } = FilterState();

  useEffect(()=>{
    loadProducts();
  },[])

  const loadProducts = async()=>{
    axios.get("http://localhost:8080/product/")
    .then((res)=>{
      setProducts(res.data);
    })
  }
  
  const transformProducts = () => {
    let sortedProducts = products;
    if (sort) {
      sortedProducts = sortedProducts.sort((a, b) =>
        sort === "highToLow" ? (a.price - b.price) : (
          sort === "lowToHigh" ? (b.price - a.price) : 
          a.count - b.count)
      );
    }
    if (sort === undefined){
      sortedProducts = sortedProducts.sort((a,b)=>a.id-b.id)
    }

    if (byCate1===false) {
      sortedProducts = sortedProducts.filter((prod) => prod.categoryId!==2);
    }
    if (byCate2===false) {
      sortedProducts = sortedProducts.filter((prod) => prod.categoryId!==3);
    }
    if (byCate3===false) {
      sortedProducts = sortedProducts.filter((prod) => prod.categoryId!==4);
    }
    if (byCate4===false) {
      sortedProducts = sortedProducts.filter((prod) => prod.categoryId!==1);
    }
    if (byCate1===false&&byCate2===false&&byCate3===false&&byCate4===false){
      sortedProducts = products;
    }

    if (searchQuery) {
      sortedProducts = sortedProducts.filter((prod) =>
        prod.name.toLowerCase().includes(searchQuery)
      );
    }
    return sortedProducts.reverse()
  };
  return (
    <div className='d-flex row'>
    <div className='home'>
      <Filter/>
      <div className='homeContainer'>
        {transformProducts().slice(firstPostIndex,lastPostIndex).map((prod)=>{
          return <SingleProduct value prod={prod} key= {prod.id}/>
        })}
        
      </div>
      
    </div>
    
    <Pagination
    totalItems={transformProducts().length}
    itemsPerPage={itemsPerPage}
    setCurrentPage={setCurrentPage}
    currentPage={currentPage}
    setItemsPerPage={setItemsPerPage}
    />
    
    </div>
  )
}
