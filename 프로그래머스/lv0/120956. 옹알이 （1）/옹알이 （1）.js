function solution(babbling) {
    let answer = 0;
    babbling.forEach(element => {
        if(element.split(/aya|ye|woo|ma/g).join("")===""){
            answer++;
        }
    });

    return answer;
}