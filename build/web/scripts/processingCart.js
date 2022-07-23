console.log("test");

function deleteCart() {
    return confirm("Do you want to delete?");
}

function updateCart(e) {
    const check = confirm("Do you want to update?");
    if (check) {
        e.closest('form').submit();
        //e.parentElement.parentElement.parentElement.parentElement.parentElement.submit()
    }
    return false;
}

function saveCart() {
    return confirm("Do you want to save?");
}
