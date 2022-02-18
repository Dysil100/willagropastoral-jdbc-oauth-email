function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "navbar-expand") {
        x.className += "navbar-expand-md";
    } else {
        x.className = "navbar-expand";
    }
}
