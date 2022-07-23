/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function deleteCategory() {
    return confirm("Do you want to delete?");
}

function updateCategory(e) {
    const check = confirm("Do you want to update?");
    console.log(e.closest('form'));
    if (check) {
        e.closest('form').submit();
    }
    return false;
}