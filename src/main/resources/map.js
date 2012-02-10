function () {
    for (var i = 0; i < this.addresses.length; i++) {
        emit(this.addresses[i].city, 1);
    }
}