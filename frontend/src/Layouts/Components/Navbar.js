import React from 'react';
import { useNavigate, Link } from 'react-router-dom';

const ROLE_PATIENT = 'ROLE_PATIENT';

export default function Navbar({ credentials, setCredentials }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    setCredentials(null);
    navigate('/');
  };

  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          Hospital Manager
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" to="/">
                Home
              </Link>
            </li>
            {credentials && credentials.roles.includes(ROLE_PATIENT) && (
              <li>
                <Link
                  className="nav-link"
                  to="/appointments/new"
                >
                  Book an Appointment
                </Link>
              </li>
            )}
          </ul>
          {credentials && (
            <button
              className="btn btn-outline-light nav-link active"
              onClick={handleLogout}
            >
              Logout
            </button>
          )}
        </div>
      </div>
    </nav>
  );
}
