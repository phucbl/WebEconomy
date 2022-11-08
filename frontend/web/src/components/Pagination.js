import React from 'react'
import { Button,Dropdown } from 'react-bootstrap'
import DropdownMenu from 'react-bootstrap/esm/DropdownMenu';

export default function Pagination({totalItems,itemsPerPage,setCurrentPage,currentPage,setItemsPerPage}) {
    const pages = []
    for (let i =1;i<=Math.ceil(totalItems/itemsPerPage);i++){
        pages.push(i)
    }
  return (
    <div className='col-md-4 offset-md-5 text-center'>
    <div className='d-flex'>
        <Dropdown className='border rounded mt-2'>
        <Dropdown.Toggle variant='white'>
            Items per Page: {itemsPerPage}
        </Dropdown.Toggle>
            <DropdownMenu>
                    <span className='dropdown-item' onClick={()=>setItemsPerPage(10)}>10</span>
                    <span className='dropdown-item' onClick={()=>setItemsPerPage(20)}>20</span>
                    <span className='dropdown-item' onClick={()=>setItemsPerPage(50)}>50</span>
            </DropdownMenu>
        </Dropdown>
        {
            pages.map((page,index)=>{
                
                return <Button key={index} onClick={()=>setCurrentPage(page)}
                variant={page == currentPage?'warning':'primary'}
                style={{margin:10, paddingLeft:20,paddingRight:20}}
                >{page}</Button>
            })
        }
    </div>
    </div>
  )
}
