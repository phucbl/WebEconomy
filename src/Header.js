import React from 'react'

export default function Header(){
    return(
        <header>
            <nav className="nav">
                <img src={require('./images/logo.png')} className="nav-logo"/>
                <h3 className='nav-logoname'>PohucSecur</h3> 
                <input className='find' placeholder='Find something' ></input>
                <ul className="nav-items"> 
                    <li>Price</li>
                    <li>About</li>
                    <li>Contact</li>
                </ul>
            </nav>
        </header>
    )
}