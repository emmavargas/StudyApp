document.getElementById("login-form").addEventListener("submit", function(event) {

    event.preventDefault();

    const username= document.getElementById("username").value;
    const password= document.getElementById("password").value;

    console.log("Username: " + username);
    console.log("Password: " + password);

    sendLoginRequest(username, password)
});

function sendLoginRequest(username,password){

    const data = {
        username: username,
        password: password
    }

    fetch("http://localhost:8080/login",{
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                document.getElementById("message").innerText = "Login Exitosoo";
                document.getElementById("message").style.color = "green";
                document.getElementById("login-form").reset();
                console.log(data.token);
            } else {
                document.getElementById("message").innerText = "Login failed";
                document.getElementById("message").style.color = "red";
                document.getElementById("login-form").reset();
                console.log("failed credentials");
            }
        })
        .catch(
            error =>{
                document.getElementById("message").innerHTML = "Error de conexion: " + error
            }
        )

}
