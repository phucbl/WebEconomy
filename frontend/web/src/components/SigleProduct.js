import React from 'react'
import {Card,Button} from 'react-bootstrap'
import Cart from '../pages/Cart'

export default function SigleProduct ({prod}) {
  return (
    <div className='products'>
        <Card>
            <Card.Img variant = 'top' src={prod.imageUrl} alt={prod.name}/>
            <Card.Body>
                <Card.Title>{prod.name}</Card.Title>
                <div style={{ paddingBottom:10}}>
                <span>{prod.price} đ</span>
                </div>
                <Button >Add to cart</Button>
                <Button style={{marginLeft:5}}>View product</Button>
            </Card.Body>
        </Card>
    </div>
  )
}

