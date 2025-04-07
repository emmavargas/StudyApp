const navcenter = document.getElementById("nav-center")
const navcentermobile = document.getElementById("nav-center-mobile")
const burger = document.getElementById('burger');
const mobileMenu = document.getElementById('mobilenav');
const closeButton = document.getElementById('close-menu')
const options = document.querySelectorAll('#menu-option-mobile')

console.log(options)
burger.addEventListener('click', () => {
    mobileMenu.classList.toggle('active');
    closeButton.classList.toggle('active');
});

closeButton.addEventListener('click', ()=> {
    mobileMenu.classList.remove('active');
    closeButton.classList.remove('active')
})

options.forEach((option)=> {
    option.addEventListener('click', ()=>{
        mobileMenu.classList.remove('active');
        closeButton.classList.remove('active')
    })
})

window.onresize=()=> {
    if(window.innerWidth >= 768) {
        open.style.display = 'none';
        close.style.display = 'none';
        navcenter.style.display = 'flex';
        navcentermobile.style.display = 'none';
    }
    else {
        open.style.display = 'flex';
        close.style.display = 'none';
        navcenter.style.display = 'none';
        navcentermobile.style.display = 'none';
    }
};

