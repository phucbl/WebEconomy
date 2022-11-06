import React from "react"
import ReactDOM from "react-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import "./index.css"
import App from "./App"
import login from "./App"
import {FilterProvider} from "./context/FilterContext"

function Page() {
    return (
        <React.StrictMode>
            <FilterProvider>
                <App />
            </FilterProvider>
        </React.StrictMode>
        
    )
}
    
ReactDOM.render(<Page/>, document.getElementById("root"))