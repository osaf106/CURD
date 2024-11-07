import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import './user_add.css'
import axios from 'axios'
import toast from 'react-hot-toast';

export default function User_add() {

    const navigate =useNavigate();
    const [user, setUser] = useState({
        fullName:"",
        email:"",
        password:""
    });
    
const inputHandler = (e)=>
{
    e.preventDefault();
    const {name, value} = e.target;
    setUser({...user,[name]:value})

    
}
const submitForm = async(e)=>
{
    e.preventDefault();
    await axios.post("http://localhost:8080/user/create",user)
    .then((res)=>{
        console.log(res)
        toast.success(res.data);      
        navigate("/");
    }).catch(error=> toast.error(error.response.data))
}


  return (
    <div className='addUser'>
        <Link to={'/'}><i className="fa-solid fa-left-long"></i></Link>
        <h3>Add New User</h3>

        <form className='addUserForm' onSubmit={submitForm}>
                <div className='inputGroup'>
                    <label htmlFor='fullName'>First Name</label>
                    <input type='text' placeholder='Enter Your name' id='fullName' name='fullName' autoComplete='off' onChange={inputHandler}/>
                </div>
                <div className='inputGroup'>
                    <label htmlFor='email'>Email</label>
                    <input type='email' placeholder='example@gmail.com' id='email' name='email' autoComplete='off' onChange={inputHandler}/>
                </div>
                <div className='inputGroup'>
                    <label htmlFor='password'>Password</label>
                    <input type='password' placeholder='Enter Your password' id='password' name='password' autoComplete='off' onChange={inputHandler}/>
                </div>
                <div className='inputGroup'>
                   <button type='submit'>Add USER</button>
                </div>
        </form>
    </div>
  )
}
