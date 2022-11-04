import { BrowserRouter, Route, Routes, Navigate} from 'react-router-dom';
import './App.css';
import Header from './components/Header'
import Home from './pages/Home'
import Cart from './pages/Cart'
import Login from './pages/Login'
import Register from './pages/Register'
import Customer from './pages/Customer'
import ViewProduct from './pages/ViewProduct'
import CustomerOrders from './pages/CustomerOrders';
import ConfirmOrder from './pages/ConfirmOrder';
import { CookiesProvider } from "react-cookie";
import ViewOrder from './pages/ViewOrder';
import Manager from './pages/admin/Manager';
import Order from './pages/admin/Order';
import OrderView from './pages/admin/OrderView';
import OrderEdit from './pages/admin/OrderEdit';
import Product from './pages/admin/Product';
import ProductEdit from './pages/admin/ProductEdit';
import ProductAdd from './pages/admin/ProductAdd';
import Cookies from 'universal-cookie';


function App() {
  const cookies = new Cookies()
  return (
    <CookiesProvider>
    <BrowserRouter>
      <Header/>
      {cookies.get('role')!=="ROLE_ADMIN"?(
        <Routes>
        <Route path='/' element={<Home />} exact/>
        <Route path='/customer/cart' element={<Cart/>} exact/>
        <Route path='/customer/login' element={<Login/>} exact/>
        <Route path='/customer/register' element={<Register/>} exact/>
        <Route path='/customer/order' element={<CustomerOrders />} exact/>
        <Route path='/customer/confirm' element={<ConfirmOrder />} exact />
        <Route path='/customer/' element={<Customer/>} exact/>
        <Route path='/product/:id' element={<ViewProduct/>} exact/>
        <Route path='/customer/order/:id' element={<ViewOrder/>} exact/>
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
      ):(
        <Routes>
          <Route path='/' element={<Home />} exact/>
          <Route path='/manager' element={<Manager/>} exact/>
          <Route path='/manager/customer' element={<Customer/>} exact/>
          <Route path='/manager/product' element={<Product/>} exact/>
          <Route path='/manager/product/add' element={<ProductAdd/>} exact/>
          <Route path='/manager/product/edit/:id' element={<ProductEdit/>} exact/>
          <Route path='/product/:id' element={<ViewProduct/>} exact/>
          <Route path='/manager/order' element={<Order/>} exact/>
          <Route path='/manager/order/:id' element={<OrderView/>} exact/>
          <Route path='/manager/order/edit/:id' element={<OrderEdit/>} exact/>
          <Route path="*" element={<Navigate to="/" replace />} />

      </Routes>
      )}
      
      
    </BrowserRouter>
    </CookiesProvider> 
  );
}
export default App;