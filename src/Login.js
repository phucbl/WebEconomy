import React from "react";

export default class Login extends React.Component{
    constructor(props){
        super(props)
        this.state = {
            "username": "",
            "password": ""
        }
    }
    setParams = (event) =>{
        this.setState({[event.target.name] : event.target.value})
    }
    login =() => {
        var myHeaders = new Headers();
        myHeaders.append("Content-Type","application/x-www-form-urlenconded");

        var urlencoded = new URLSearchParams();
        // urlencoded.append("username", this.state.username);
        // urlencoded.append("password", this.state.password);
        urlencoded.append("username", "123");
        urlencoded.append("password", "123");

        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            body: urlencoded,
            redirect: 'follow'
        };
        fetch("http://localhost:3000/",requestOptions)
            .then(response=>{
                console.log(response)
                if (response.ok){
                    return response.json()
                }
                throw Error(response.status)
            })
            .then(result => {
                console.log(result)
                localStorage.setItem("accessToken",result.accessToken)
                alert("Thanh Cong")
            })
            .catch(error => {
                console.log('error',error)
                alert("Username, password are wrong")
            });
    }
    render() {
        return <form>
            <div>
                <label>Username:</label>
                <input type="text" name ="username" onChange={this.setParams}/>
            </div>
            <div>
                <label>Password:</label>
                <input type="text" name ="password" onChange={this.setParams}/>
            </div>
            <div>
                
                <button type="button" onClick={this.login}>Login</button>
            </div>
        </form>
    }
}
