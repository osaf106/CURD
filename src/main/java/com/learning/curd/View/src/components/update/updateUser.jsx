import React, { useEffect, useState } from 'react'
import '../addUser/user_add.css'
import { Link, useNavigate, useParams } from 'react-router-dom'
import axios from 'axios';
import toast, { Toaster } from 'react-hot-toast';
export default function UpdateUser() {

    const users =
        {
        fullName:"",
        email:"",
        password:""
    }
    const {id} = useParams();
    const navigate =useNavigate();
    
    useEffect(()=>{
        const edit = async ()=>{
            console.log(id)
            await axios.get(`http://localhost:8080/user/get/${id}`)
            .then(res=>{
                setUser(res.data);
                console.log(res.data)
            })
            .catch((error)=>{
                console.log(error)
            })
            
        }
        edit();
    },[])

    const [user,setUser] = useState(users);

    const handleUserValue = (e)=>{      
        const{name, value} = e.target;
        setUser({...user,[name]:value})
        console.log(user)
    }
    const submitUpdate = async (e)=>{
        
        e.preventDefault();
        await axios.put(`http://localhost:8080/user/update/${id}`,user)
        .then((res)=>{
            toast.success(res.data.message)
            navigate('/')
        })
        .catch((error)=>{
        })
        
    }

  return (
    <div className='addUser'>
    <Link to={'/'}><i className="fa-solid fa-left-long"></i></Link>
    <h3>Update User</h3>

    <form className='addUserForm' onSubmit={submitUpdate}>
            <div className='inputGroup'>
                <label htmlFor='fullName'>First Name</label>
                <input type='text' placeholder='Enter Your name' id='fullName' name='fullName' autoComplete='off' onChange={handleUserValue} value={user.fullName}/>
            </div>
            <div className='inputGroup'>
                <label htmlFor='email'>Email</label>
                <input type='email' placeholder='example@gmail.com' id='email' name='email' autoComplete='off' onChange={handleUserValue} value={user.email}/>
            </div>
            <div className='inputGroup'>
                <label htmlFor='password'>Password</label>
                <input type='text' placeholder='Enter Your password' id='password' name='password' autoComplete='off' onChange={handleUserValue} value={user.password}/>
            </div>
            <div className='inputGroup'>
               <button type='submit'>Update USER</button>
            </div>
    </form>
</div>
  )
}
