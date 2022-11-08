import  { React,useEffect} from 'react'
import {Card,Button} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import Cookies from 'universal-cookie';
import axios from 'axios';


export default function SigleProduct ({prod}) {
    const cookies = new Cookies()
    
    useEffect(()=>{
      addToCart();
    },[])

    const addToCart = async(e)=>{
      e.preventDefault()
      axios.post(`http://localhost:8080/product/${prod.id}`,{
        'id':{'customerId':cookies.get('customerId'),'productId':prod.id},'quantity':1
      }
      ,{
        headers:{
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + cookies.get('token'),
            'customerId': cookies.get('customerId')
        }
      })
      alert ("Added product to cart")
    }
  return (
    <div className='products'>
        <Card>
            <Card.Img variant = 'top' src={prod.imageUrl} alt={prod.name}/>
            <Card.Body>
                <Card.Title>{prod.name}</Card.Title>
                <div style={{ paddingBottom:10}}>
                {prod.status==true?(<span>{prod.price} đ</span>):(<span class="text-danger">No more sell</span>)}
                </div>
                <div style={{ paddingBottom:10}}>
                Sold: {prod.count}
                </div>
                <Button onClick={(e)=>addToCart(e)} disabled={cookies.get('role')!=="ROLE_USER"||prod.status===false}>Add to cart</Button>
                <Link to={'/product/'+prod.id}>
                <Button variant='success' style={{marginLeft:5}}>View product</Button>
                </Link>
                
            </Card.Body>
        </Card>
    </div>
  )
}

