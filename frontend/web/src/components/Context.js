import {React,createContext, useContext,state,dispatch} from 'react'


const Cart = createContext();

const Context = ({children}) => {
  return (
    <Cart.Provider value={{state,dispatch}}>
        {children}
    </Cart.Provider>
  )
}

export default Context;

export const CartState=()=>{
  return useContext(Cart)
}