import React from 'react'
import { Button } from 'react-bootstrap'

export default function Pagination({totalItems,itemsPerPage,setCurrentPage,currentPage}) {
    const pages = []
    for (let i =1;i<=Math.ceil(totalItems/itemsPerPage);i++){
        pages.push(i)
    }
  return (
    <div>
        {
            pages.map((page,index)=>{
                return <Button key={index} onClick={()=>setCurrentPage(page)}
                variant={page == currentPage?'warning':'primary'}
                style={{margin:10, paddingLeft:20,paddingRight:20}}
                >{page}</Button>
            })
        }
    </div>
  )
}
