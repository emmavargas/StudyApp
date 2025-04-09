const navcenter = document.getElementById("nav-center")
const navcentermobile = document.getElementById("nav-center-mobile")
const burger = document.getElementById('burger');
const menuIcon = document.getElementById('open-menu')
const mobileMenu = document.getElementById('mobilenav');
const options = document.querySelectorAll('#menu-option-mobile')

burger.addEventListener('click', () => {
    if (mobileMenu.classList.contains('active')) {
        menuIcon.src = './assets/img/menu.svg'
    }
    else {
        menuIcon.src = './assets/img/close.svg'
    }
    mobileMenu.classList.toggle('active');
});

options.forEach((option)=> {
    option.addEventListener('click', ()=>{
        mobileMenu.classList.remove('active');
        menuIcon.src = './assets/img/menu.svg'
    })
})

