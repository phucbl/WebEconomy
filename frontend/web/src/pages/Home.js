import  { React,useEffect, useState } from 'react'
import SingleProduct from '../components/SigleProduct'
import "../components/style.css";
import Filter from '../components/Filter';
import axios from 'axios'

export default function Home () {

  const [products, setProducts] = useState([]);

  useEffect(()=>{
    loadProducts();
  },[])

  const loadProducts = async()=>{
    const result=await axios.get("http://localhost:8080/product/");
    setProducts(result.data);
  }
  

  

  return (

    <div className='home'>
      <Filter/>
      <div className='productContainer'>
        {products.map((prod)=>{
          return <SingleProduct prod={prod} key= {prod.id}/>
        })}
      </div>
      
      </div>
  )
}
