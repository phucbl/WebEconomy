import  { React,useEffect, useState } from 'react'
import { useParams, useNavigate,Link } from 'react-router-dom'
import {Image,Button, Dropdown} from 'react-bootstrap'
import axios from 'axios';
import DropdownMenu from 'react-bootstrap/esm/DropdownMenu';
import Cookies from 'universal-cookie';
export default function ProductEdit() {
    const navigate= new useNavigate()
    const cookies = new Cookies()
    const {id} = useParams();
    const [categories,setCategories] = useState([])
    const [product, setProduct] = useState({
        name:"",
        imageUrl:"",
        price:"",
        origin:"",
        categoryId:"",
        description:""
    })
    const [file, setFile]=useState([]);
    const{name,imageUrl,price,origin,categoryId,description}=product
    
    useEffect(()=>{
        loadProduct()
    },[])
    useEffect(()=>{
        loadCategories()
    },[])

    const loadCategories = async ()=>{
        const result = await axios.get("http://localhost:8080/category")
        setCategories(result.data)
      }
    const loadProduct = ()=>{
      axios.get(`http://localhost:8080/product/${id}`).then((res)=>{
        setProduct(res.data)
      })
    }
    const onInputChange =  (e)=>{
        setProduct({...product,[e.target.name]:e.target.value})
    }

    const onSubmit = (e)=>{
        if(product.price===""||product.name===""||product.categoryId===""){alert("Missing fields")}else{
            axios.put(`http://localhost:8080/product/${id}`,{
                'name':product.name,
                'imageUrl':product.imageUrl,
                'price':product.price,
                'origin':product.origin,
                'categoryId':product.categoryId,
                'description':product.description
        },{
            headers:{
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + cookies.get('token')
            }
        }).then(()=>{
            navigate("/manager/product")
        }).catch((error)=>{
            alert(error.response.data.message)
        })
        }
        
    }
    const uploadSingleFile= (e) =>{
        let fileType=e.target.files[0].name.split('.').reverse()[0]
        axios.get(`/images/${e.target.files[0].name}`).catch((error) => {
            alert("File was not saved in Images Folder")
            e.target.value = null;
        })
        if (fileType==="jpg"||fileType==="jpeg"||fileType==="png"){
            setFile(e.target.files[0])
        }else{
            alert("File type not supported")
            e.target.value = null;
        }
        
    };
    const  upload= (e)=> {
        e.preventDefault();
        {file.name!==undefined?(
            setProduct({...product,["imageUrl"]:"/images/"+file.name})
        ):(
            alert('Select an image')
        )}
    };
  return (
    <div>
        <div className='col-md-8 offset-md-2 border rounded p-4 mt-2'>
            <form onSubmit={(e)=>onSubmit (e)}>
            <div className='d-flex'>
                <div className='d-flex row'>
                    <Image src={imageUrl} className="img-fluid" alt="Responsive"/>
                    <Button onClick={(e)=>upload(e)}>Change Image</Button>
                    <input type="file"  className="form-control"  onChange={(e)=>uploadSingleFile(e)} accept="image/png, image/jpeg"/>
                </div>
                <div style={{paddingLeft:100}} className="d-flex row">
                    <h2>Name</h2>
                    <input 
                    className="form-control"
                    name="name"
                    value={name}
                    onChange={(e)=> onInputChange(e)}
                    />
                    <h2>Price</h2>
                    <input
                    className="form-control" 
                    name="price"
                    value={price}
                    onChange={(e)=> onInputChange(e)}
                    />
                    <h2>Origin</h2>
                    <input
                    className="form-control" 
                    name="origin"
                    value={origin}
                    onChange={(e)=> onInputChange(e)}
                    />
                    <h2>Category</h2>
                    <input
                    type={"number"}
                    className="form-control" 
                    name="categoryId"
                    value={categoryId}
                    onChange={(e)=> onInputChange(e)}
                    />
                    <Dropdown>
                    <Dropdown.Toggle variant='white'>
                        List Category
                    </Dropdown.Toggle>
                        <DropdownMenu>
                                {categories.sort((a,b)=>a.id-b.id).map((category)=>{
                                    return(
                                        <span className='dropdown-item' 
                                        onClick={()=>setProduct({...product,["categoryId"]:category.id})}
                                        key={category.id}>{category.id} : {category.name}</span>
                                    )
                                })}
                                
                        </DropdownMenu>
                    </Dropdown>
                </div>
            </div>
            <div style={{paddingTop:20}}>
                <h2>Description</h2>
                <input
                        className="form-control" 
                        name="description"
                        value={description}
                        onChange={(e)=> onInputChange(e)}
                />
            </div>
            </form>
            <div className='text-center' style={{paddingTop:20}}>
                <Button variant='warning' onClick={(e)=>onSubmit(e)}>Save Edit</Button>
                <Link to="/manager/product/">
                    <Button style={{marginLeft:20}}>Back</Button>
                </Link>
                
                </div>
        </div>
        
    </div>
  )
}
