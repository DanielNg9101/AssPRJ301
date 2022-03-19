$(document).ready(function () {
    $('.addToCartLink').click(function (e) {
        e.preventDefault();
        // console.log(e.target.search)
        const plantID = e.target.search.split("=")[1]
        console.log(plantID)
        axios.get('addToCart?plantID=' + plantID)
    });
});

