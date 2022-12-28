function solution(denum1, num1, denum2, num2) {
    let answer = [];
    let son = denum1 * num2 + denum2 * num1;
    let mother = num1 * num2;
    let uclid = function (a, b) {
        if (a % b === 0) return b;
        else return uclid(b, a % b);
    }
    let gcd = uclid(son, mother);
    answer.push(son / gcd);
    answer.push(mother / gcd);

    return answer;
}