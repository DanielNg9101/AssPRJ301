/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('.addToCartLink').click(function (e) {
        e.preventDefault();
        // console.log(e.target.search)
        const reconform = confirm("Add to cart?");
        if (reconform) {
            const plantID = e.target.search.split("=")[1]
            console.log(plantID)
            axios.get('addToCart?plantID=' + plantID)
        }
    });
});

function editPlant() {
    return confirm("Do you want to edit?");
}

