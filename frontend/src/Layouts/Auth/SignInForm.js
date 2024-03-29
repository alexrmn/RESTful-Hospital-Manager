import React, { useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import hospitalImage from '../../Images/hospital.jpg'
import { toast } from 'react-toastify';



export default function SignInForm({ setCredentials }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/auth/signin', {
                username,
                password
            });
            const { data: { token, roles, userId } } = response;
            setCredentials({ token, roles , userId}); // store token and roles in parent component
        } catch (error) {
            console.error(error);
            toast.error (error.response.data, {
                position: "top-center",
                autoClose: 1500,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            })
        }
    };
    return (
        <div className="container mt-5">
            <div className="row align-items-center">
                <div className="col-md-4 order-md-2 ">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <input
                                type="text"
                                required
                                className="form-control"
                                id="username"
                                value={username}
                                onChange={(event) => setUsername(event.target.value)}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <input
                                type="password"
                                required
                                className="form-control"
                                id="password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                            />
                        </div>
                        <button type="submit" className="btn btn-primary mt-4">
                            Sign In
                        </button>
                        <div className='mx-auto mt-2'>
                            <Link to='/register' className=''>
                                Create a new account
                            </Link>
                        </div>
                    </form>
                </div>
                <div className="col-md-8 order-md-1">
                    <img src={hospitalImage} alt='Hospital' className="img-fluid" />
                </div>
            </div>
        </div>

    );
}