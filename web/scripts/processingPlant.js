/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function editPlant(e) {
    const check = confirm("Do you want to edit?");
    if (check) {
        e.closest('form').submit();
    }
    return false;
}

function deletePlant() {
    return confirm("Do you want to delete?");
}
