/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let btn = document.querySelector('.btn');
let name = document.querySelector('.name');
btn.addEventListener("click", () => {
    const data = {
        name: name.value
    };
    console.log(name.value);
    let http = new XMLHttpRequest();
    http.open("POST", "/WebApplication1/buy");
    http.setRequestHeader("Content-type", "application/json;charset=UTF-8");
    http.send(JSON.stringify(data));

    http.onload = function () {
        console.log(http.response);
    };
});

