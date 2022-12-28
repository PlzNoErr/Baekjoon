function solution(common) {
    let answer = common[0];
    let rule = common[1] - common[0];
    if (common[1] + rule == common[2]) for (let i = 0; i < common.length; i++) answer += rule;
    else for (let i = 0; i < common.length; i++) answer *= common[1] / common[0];
    return answer;

    return answer;
}