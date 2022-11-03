import  { React,useEffect, useState } from 'react'
import SingleProduct from '../components/SingleProduct'
import "../components/style.css";
import Filter from '../components/Filter';
import axios from 'axios'
import Header from '../components/Header'

export default function Home () {
  const [products, setProducts] = useState([]);
  
  useEffect(()=>{
    loadProducts();
  },[])

  const loadProducts = async()=>{
    axios.get("http://localhost:8080/product/")
    .then((res)=>{
      setProducts(res.data);
    })
    
  }
  return (
    
    <div className='home'>
      
      <Filter/>
      <div className='productContainer'>
        {products.map((prod)=>{
          return <SingleProduct prod={prod} key= {prod.id}/>
        }).reverse()}
      </div>
      
    </div>
  )
}
