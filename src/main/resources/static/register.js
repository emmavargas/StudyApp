document.getElementById("register-form").addEventListener("submit", function(event) {

    event.preventDefault();


    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const email = document.getElementById("email").value;
    const name = document.getElementById("name").value;
    const lastname = document.getElementById("lastname").value;


    sendRegisterRequest(username, password, email, name, lastname);

});

function sendRegisterRequest(username, password,email, name, lastname){

    const dataRegister = {
        username: username,
        password: password,
        email: email,
        name: name,
        lastname: lastname
    }

    fetch("http://localhost:8080/register",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(dataRegister)
        }
    )
    .then(response => response.json())
    .then(data => {
        if(data.success){
            document.getElementById("message").innerText = "Registro Exitoso";
            document.getElementById("message").style.color = "green";
            document.getElementById("register-form").reset();
            console.log(data.token);
        }
        else{
            document.getElementById("message").innerText = "Registro fallido";
            document.getElementById("message").style.color = "red";
            document.getElementById("register-form").reset();
            console.log("failed credentials");
        }
    })
    .catch(
        error => {
            document.getElementById("message").innerHTML = "Error de conexion: " + error
        }
    )
}