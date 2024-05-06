/*the complete project is in the following link:
https://github.com/vkive/facebook-website.git
Follow me on Codepen
*/
var settingsmenu = document.querySelector(".settings-menu");
var darkBtn =document.getElementById("dark-btn");

function settingsMenuToggle(){
    settingsmenu.classList.toggle("settings-menu-height");
}


if(localStorage.getItem("theme") == "light"){
    darkBtn.classList.remove("dark-btn-on");
    document.body.classList.remove("dark-theme");

}
else if(localStorage.getItem("theme") == "dark"){
    darkBtn.classList.add("dark-btn-on");
    document.body.classList.add("dark-theme");

}
else{
    localStorage.setItem("theme","light");

}