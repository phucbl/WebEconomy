import React from "react"
import ReactDOM from "react-dom"
import 'bootstrap/dist/css/bootstrap.min.css'
import "./index.css"
import App from "./App"
import Context from "./components/Context"



function Page() {
    return (
        <React.StrictMode>
            <Context>
                <App />
            </Context>
        </React.StrictMode>
        
    )
}
    
ReactDOM.render(<Page/>, document.getElementById("root"))