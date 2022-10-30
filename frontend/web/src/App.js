import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Header from './components/Header'
import Home from './pages/Home'
import Cart from './pages/Cart'
import Login from './pages/Login'
import Register from './pages/Register'
import Customer from './pages/Customer'
import ViewProduct from './pages/ViewProduct'
import { CookiesProvider } from "react-cookie";

function App() {
  return (
    <CookiesProvider>
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path='/' element={<Home />} exact/>
        <Route path='/customer/cart' element={<Cart/>} exact/>
        <Route path='/customer/login' element={<Login/>} exact/>
        <Route path='/customer/register' element={<Register/>} exact/>
        <Route path='/customer/' element={<Customer/>} exact/>
        <Route path='/product/:id' element={<ViewProduct/>} />
      </Routes>
    </BrowserRouter>
    </CookiesProvider>
  );
}

export default App;
