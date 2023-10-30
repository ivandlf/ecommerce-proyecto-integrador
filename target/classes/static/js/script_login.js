const $btnSignIn= document.querySelector('.sign-in-btn'),
      $btnSignUp = document.querySelector('.sign-up-btn'),  
      $signUp = document.querySelector('.sign-up'),
      $signIn  = document.querySelector('.sign-in');
const btnIniciarSesion = document.querySelector('#btnIniciarSesion')
const btnRegistrarse = document.querySelector('#btnRegistrarse')

document.addEventListener('click', e => {
    if (e.target === $btnSignIn || e.target === $btnSignUp) {
        $signIn.classList.toggle('active');
        $signUp.classList.toggle('active')
        
    }
    if (e.target == $btnSignIn){
      console.log('boton sign in')
    }
    if (e.target == $btnSignUp){
      console.log('boton sign up')
      
    }
});

btnIniciarSesion.addEventListener("click", iniciarSesion)

btnRegistrarse.addEventListener("click", crearUsuario)
async function crearUsuario(){
  let datos = {};
  datos.nombre = document.getElementById('txtNombreSingUp').value;
  datos.email = document.getElementById('txtemailSingUp').value;
  datos.password = document.getElementById('txtpassSingUp').value;
  console.log(datos)
  fetch('api/user', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  })
  .then(response => {
    if(response.ok){
      console.log("usuario creado con exito")
    }else{
      console.log("fallo al crear usuario")
    }
  })
}

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