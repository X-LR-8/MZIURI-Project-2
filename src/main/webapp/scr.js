async function register(){
    var username=document.getElementById("username").value;
    var password= document.getElementById("password").value;
    var url="/messenger"+"/register"+`?username=${username}&password=${password}`;
    var response = await fetch(url, { method: "POST" });
    console.log(response.ok);
    if(response.ok){
        alert("User named "+username+" has successfully registered "+response.status);
    }
    else{
        alert("Could not register user: "+response.status);
    }
    deletevalue("username");
    deletevalue("password");
}
function deletevalue(id){
    document.getElementById(id).value=""
}
async function Message(){
    var user=document.getElementById("user").value;
    var message= document.getElementById("message").value;
    var url="/messenger"+"/message"+`?user=${user}&message=${message}`;
    var response = await fetch(url, { method: "POST" });
    if(response.ok){
        alert("successfully sent a message to the User named "+user+" "+response.status);
    }
    else{
        alert("Could not send message: "+response.status);
    }
    deletevalue("user");
    deletevalue("message")
}
async function getmessage(){
    var username2=document.getElementById("username2").value;
    var password2= document.getElementById("password2").value;
    var url="/messenger"+"/message"+`?username=${username2}&password=${password2}`;
    var response = await fetch(url, { method: "GET" });
    if(!response.ok) {
        alert("Cant find User named " + username2 + " " + response.status);
    }
    deletevalue("user");
    deletevalue("message");

    var body = await response.text();
    var message=body.split('\n');
    var messagelist="";
    for(var i=0; i<message.length; i++){
        if(message[i]!=""){
            messagelist+=`<li>${message[i]}</li>`;
        }
    }
    var div = document.getElementById("inbox");
    div.innerHTML = `<ul>${messagelist}</ul>`;
}


// Request-ის გაგზავნა
// async function register() {
//     var url = webserverName + servletUrl + '?param1=param1Value' + '&param2=param2Value';
//     var method = "POST" ან "GET"
//     var response = await fetch(url, { method: "POST" });
//
//     // Response body-ს მიღება
//     var body = await response.text();
//
//     // HTML ელემენტის დამატება/შეცვლა
//     var div = document.getElementById("some-div-id");
//     div.innerHTML = 'some html code here';
// }