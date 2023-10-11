package algorithm.programmers.highScoreKit.binarySearch;

import java.util.Arrays;

/**
 * 징검다리
 * 이분탐색으로 최소길이를 갱신해가며 탐색해야한다.
 * 이떄 길이 갱신의 조건을 잡아내는게 관건이었다.
 */
public class JingGumDari {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);

        answer = parametricSearch(rocks,distance,n);

        return answer;
    }

    /**
     * 정렬된 data를 입력받아 삭제하는 돌의 갯수를 만족하는 최적의 답을 찾는다.
     */
    public int parametricSearch(int[] data,int end, int standardNum){
        int left=0,right=end; // 시작지점 =0, 끝지점 = 마지막 위치
        int mid;
        int answer = 0;
        //이분 탐색으로 조건을 만족하는 최소길이를 찾는다
        while(left<=right){
            mid= (left+right) >>1;
            if(getCountToRemove(data,end,mid) <=standardNum){
                //최소 길이를 만족하기 위해 삭제해야하는 돌의 수가 기준보다 작으면 최소길이 증가
                answer = mid;
                left = mid+1;
            }else{
                //최소 길이를 만족하기 위해 삭제해야하는 돌의 수가 기준보다 크면 최소길이 감소
                right = mid-1;
            }
        }
        return answer;
    }
    public int getCountToRemove(int[] data, int end, int standardDist){
        int before = 0;
        int cur;
        int result = 0; // 지워야할 돌의 갯수
        for(int i=0; i<data.length; i++){
            cur = data[i];
            if(cur-before < standardDist){
                // 현재의 최소길이를 만족하지 못하므로 삭제해야할 돌을 추가하고 갱신한다.
                result++;
                continue;
            }
            before = cur;
        }
        if(end-before < standardDist) result++;
        return result;
    }
}
