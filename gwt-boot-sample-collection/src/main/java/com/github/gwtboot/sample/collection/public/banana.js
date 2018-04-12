/*
 * JavaScript Banana.
 *
 * This class will be called from GWT with JS Interop.
 */
Banana = function () {
    this.x = 40;
    this.y = 2;
};
Banana.prototype.sum = function () {
    return this.x + this.y;
};