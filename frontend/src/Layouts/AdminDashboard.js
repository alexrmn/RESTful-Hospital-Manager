import React from 'react'
import { Link } from 'react-router-dom'

export default function AdminDashboard() {
  return (
    <div className="container mt-5">
      <h1>Admin Dashboard</h1>
      <div className="my-4">
        <Link to="/specialties" className='btn btn-primary m-2'>Specialties</Link>
        
        <Link to="/doctors" className='btn btn-primary'>Doctors</Link>
      </div>
    </div>
  )
}
