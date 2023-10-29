const $btnSignIn= document.querySelector('.sign-in-btn'),
      $btnSignUp = document.querySelector('.sign-up-btn'),  
      $signUp = document.querySelector('.sign-up'),
      $signIn  = document.querySelector('.sign-in');
const btnIniciarSesion = document.querySelector('#btnIniciarSesion')

document.addEventListener('click', e => {
    if (e.target === $btnSignIn || e.target === $btnSignUp) {
        $signIn.classList.toggle('active');
        $signUp.classList.toggle('active')
    }
    if (e.target == $btnSignIn){
        iniciarSesion();
    }
});

btnIniciarSesion.addEventListener("click", iniciarSesion)

async function iniciarSesion() {
    let datos = {};
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    console.log(datos)
  
    fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(datos)
    })
    .then(response => {
        if (response.ok) {
          // Extraer el token del encabezado de respuesta
          const token = response.headers.get('Authorization');
    
          // Almacenar el token en el Local Storage
          localStorage.setItem('token', token);
          alert('Sesion iniciada correctamente con el token')
          window.location.href='index.html'
        } else {
          // Manejar errores de autenticación
          alert('El usuario o contraseña son incorrectos, intente nuevamente.')
        }
      })
      .catch(error => {
        // Manejar otros errores
      });
  }