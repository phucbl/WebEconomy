import {createContext, React,useContext, useEffect, useState,useReducer} from "react"
import { productReducer } from "./Reducers"
import axios from 'axios'
import Cookies from "universal-cookie"
const FilterContext = createContext()

export function FilterState(){
    return useContext(FilterContext)
}


export function FilterProvider ({children}){
    const cookies = new Cookies()
    
    const [state, dispatch] = useReducer(productReducer, {
        byCate1: false,
        byCate2: false,
        byCate3: false,
        byCate4: false,
        searchQuery: ""
    });

    return (
        <FilterContext.Provider value={{state,dispatch}}>
            {children}
        </FilterContext.Provider>
    )
}