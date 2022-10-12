import React from "react"
import ReactDOM from "react-dom"
import "./style.css"
import Header from "./Header"
import Footer from "./Footer"
import MainContent from "./MainContent"
import Item from "./Item"
import Login from "./Login"

function Page() {
        
    return (
        <div className="items">
            {/* <Header/>        
            <MainContent/>
            <Footer/> 
            <Item 
                name ="BCD"
                phone = "234"
                mail = "bcd@mail"/>
            <Item 
                name ="ERG"
                phone = "234"
                mail = "erg@mail"/>
            <Item 
                name ="QWA"
                phone = "234"
                mail = "qwa@mail"/>
            <Item 
                name ="ERW"
                phone = "234"
                mail = "erw@mail"/>
            */}
            <Login />
        </div>
    )
}
    
ReactDOM.render(<Page/>, document.getElementById("root"))