// src\pages\Welcome\index.js

import { useNavigate } from 'react-router-dom';
import './styles.css';
import logo from '../../assets/images/logo.png';

function WelcomePage() {
    const navigate = useNavigate();

    return (
        <div className='welcome-page'>
            <div className='container'>
                <img src={logo} alt="Logo da empresa" className='logo' />
                <h1>Bem-vindo ao sistema de cadastro de jogadores!</h1>
                <button onClick={() => navigate('/cadastrar')} className='link-cadastrar'>
                    Cadastrar Jogador
                </button>
                <button onClick={() => navigate('/usuarios')} className='link-lista'>
                    Ver Lista de jogadores
                </button>
            </div>
        </div>
    );
}

export default WelcomePage;