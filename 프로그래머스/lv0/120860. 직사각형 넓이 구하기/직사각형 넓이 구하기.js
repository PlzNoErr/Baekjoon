function solution(dots) {
    let a = 0;
    let b = 0;
    for (let i = 1; i < 4; i++) {
        if (dots[0][0] == dots[i][0]) {
            a = Math.abs(dots[0][1] - dots[i][1])
            break;
        }
    }

    for (let i = 1; i < 4; i++) {
        if (dots[0][1] == dots[i][1]) {
            b = Math.abs(dots[0][0] - dots[i][0])
            break;
        }
    }
    return a * b;
}