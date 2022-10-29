import { BrowserRouter, Route, Routes} from 'react-router-dom';
import './App.css';
import Header from './components/Header'
import Home from './pages/Home'
import Cart from './pages/Cart'
import Login from './pages/Login'

function App() {
  return (
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path='/' element={<Home />} exact/>
        <Route path='/customer/cart' element={<Cart/>} exact/>
        <Route path='/customer/login' element={<Login/>} exact/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
