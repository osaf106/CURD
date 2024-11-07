import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import './user.css'
import axios from 'axios';
import toast from 'react-hot-toast';
export const AllUserGet = () => {

    const [userData, setUserData] = useState([]);


    useEffect( ()=>{

       const fetchData = async ()=>{
           await axios.get('http://localhost:8080/user/all')
           .then(res => {
            console.log(res)
            setUserData(res.data)
           })
           
            
            // setUserData(res.data);
       }
       fetchData();
    },[])

    const DeleteUser = (id)=>{
        const deleteUserFromDB = async ()=>{
            console.log(id);
            const response = await axios.delete(`http://localhost:8080/user/delete/${id}`)
            console.log(response)
            setUserData((preId)=> preId.filter((user) => user.id!== id))
            toast.success(response.data.message)
        }
        deleteUserFromDB();
    }
  return (
    <div className='userTable'>
        
        <Link to={'/add'} className='addButton'>Add User</Link>
        <table border={1} cellPadding={10} cellSpacing={0}>
                <thead>
                        <tr>
                                <th>Serial Number</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Actions</th>
                        </tr>
                </thead>
                <tbody>
                        {
                            userData.map((data,i)=>{
                                
                                return(
                                    <tr key={data.id}>
                                        <td>{i+1}</td>
                                        <td>{data.fullName}</td>
                                        <td>{data.email}</td>
                                        <td className='actionButton'>
                                            <button onClick={()=>{DeleteUser(data.id)}} ><i className="fa-solid fa-trash"></i></button>
                                            <Link to={`/edit/${data.id}`}><i className="fa-solid fa-user-pen"></i></Link>
                                        </td>
            
                                    </tr>  
                                )
                            })
                        } 
                        
                                             
                </tbody>

        </table>
    </div>
  )
}
