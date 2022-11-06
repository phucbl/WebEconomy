import  { React,useEffect, useState } from 'react'
import {Button,Image} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import axios from 'axios'
import Cookies from 'universal-cookie'

export default function CategoryManager(){
    const cookies = new Cookies();
    const [name,setName] = useState([])
    
    const [editName,setEditName] = useState([])
    const [categories, setCategories] = useState([])
    useEffect(()=>{
        loadCategories();
      },[])
    const loadCategories = ()=>{
        axios.get("http://localhost:8080/category",{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then((res)=>{
        setCategories(res.data);
      })}
    const deleteCategory = (id)=>{
        axios.delete(`http://localhost:8080/category/${id}`,{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then(()=>{loadCategories()})
    }
    const onInputChange = (e) =>{
        setName(e.target.value)
    }
    const changeName = (e) =>{
        setEditName (e.target.value)
    }
    
    const addCategory = (e)=>{
        axios.post("http://localhost:8080/category",{
            'name':name
        },{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then(()=>{
        loadCategories()
        setName([])
        })
    }
    const editCategory = (id)=>{
        axios.put(`http://localhost:8080/category/${id}`,{
            'name':editName
        },{
          headers:{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + cookies.get('token')
          }
      }).then(()=>{
        loadCategories()
        setEditName([])
        })
    }
    
    return(
        <div className='col-md-6 offset-md-3 border rounded p-4 mt-2'>
            <div className='text-center m-4'> 
                
                    <Button className="btn btn-primary" data-toggle="modal" data-target="#modal1">
                    Add Category
                    </Button>
                    <div className="modal fade" id="modal1" tabIndex="-1" role="dialog" aria-labelledby="modal1Label" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="modal1Label">Add Category</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                        <label htmlFor="categoryName" className='form-label'>
                            Category Name
                        </label>
                        <input 
                        type={"text"}
                        className="form-control"
                        name="categoryName"
                        value={name}
                        onChange={(e)=> onInputChange(e)}/>
                        </div>
                        <div className="modal-footer text-center">
                            <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                            onClick={(e)=>addCategory(e)} data-dismiss="modal"
                            >Add</button>
                            <button type="button" className="btn btn-secondary" onClick={()=>setName([])} data-dismiss="modal">Close</button>
                            
                        </div>
                        </div>
                    </div>
                    </div>
            </div>
            <table className="table table-striped">
                <thead>
                    <tr>
                    <th scope="col" className='text-center m-4' style={{paddingLeft:150}}>#</th>
                    <th scope="col" className='text-center m-4'>Category Name</th>
                    </tr>
                </thead>
                <tbody>
                {
                    categories.sort((a, b) => a.id - b.id).map((category)=>(
                        <tr key={category.id}>
                            <th scope="row" className='text-center m-4' style={{paddingLeft:150}} >{category.id}</th>
                            
                            <td className='text-center m-4'>{category.name}</td>
                            <td className='text-center m-4'>
                                
                                <Button variant="warning" style={{marginLeft:10,marginRight:10,paddingLeft:30,paddingRight:30}} data-toggle="modal" data-target="#modal3"
                                >Edit</Button>
                                <div className="modal fade" id="modal3" tabIndex="-1" role="dialog" aria-labelledby="modal3Label" aria-hidden="true">
                                <div className="modal-dialog" role="document">
                                    <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="modal3Label">Edit Category : {category.name}</h5>
                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                    <label htmlFor="categoryName" className='form-label'>
                                        Category Name
                                    </label>
                                    <input 
                                    type={"text"}
                                    className="form-control"
                                    name={JSON.stringify(category)}
                                    value={editName}
                                    onChange={(e)=> changeName(e)}/>
                                    </div>
                                    <div className="modal-footer text-center">
                                        <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                                        onClick={()=>editCategory(category.id)} data-dismiss="modal"
                                        >Edit</button>
                                        <button type="button" className="btn btn-secondary" onClick={()=>setEditName([])} data-dismiss="modal" >Close</button>
                                        
                                    </div>
                                    </div>
                                </div>
                                </div>
                                <Button disabled={category.numberProduct!==0} variant="danger" data-toggle="modal" data-target="#modal2">Delete</Button>
                                <div className="modal fade" id="modal2" tabIndex="-1" role="dialog" aria-labelledby="modal2Label" aria-hidden="true">
                                <div className="modal-dialog" role="document">
                                    <div className="modal-content">
                                    <div className="modal-header">
                                    <h5 className="modal-title" id="modal2Label">Delete Category</h5>
                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div className="modal-body">
                                        You want to delete category: {category.name}?
                                    </div>
                                    <div className="modal-footer text-center">
                                        <button type="button" className="btn btn-primary" style={{paddingLeft:20,paddingRight:20,marginRight:50}}
                                        onClick={()=>deleteCategory(category.id)} data-dismiss="modal"
                                        >Delete</button>
                                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                                        
                                    </div>
                                    </div>
                                </div>
                                </div>
                                
                            </td>
                        </tr>
                    )).reverse()
                }
                </tbody>
                
            </table>
            
        </div>
        
    )
}