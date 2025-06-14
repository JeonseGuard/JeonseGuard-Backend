<div align="middle">
<img alt="JeonseGuard" src="https://github.com/user-attachments/assets/0ba3ba18-f649-488c-814a-74e9c2400537">

[![Live: JeonseGuard](https://img.shields.io/badge/Live-JeonseGuard-1E3A8A?style=flat-square&logo=vercel&logoColor=white)](https://jeonse-guard-frontend.vercel.app/)
[![Swagger Docs](https://img.shields.io/badge/Swagger-API%20Docs-0F766E?style=flat-square&logo=swagger&logoColor=white)](https://jeonseguard.duckdns.org/swagger-ui/index.html#/)
[![Release](https://img.shields.io/badge/Release-v5.0-FACC15?style=flat-square&logo=github&logoColor=white)](https://github.com/JeonseGuard/JeonseGuard-Backend/releases)
[![License](https://img.shields.io/badge/License-MIT-4F46E5?style=flat-square&logo=open-source-initiative&logoColor=white)](https://github.com/JeonseGuard/JeonseGuard-Backend/blob/develop/LICENSE)

</div>

# 프로젝트 소개
<div align="justify">
<b>JeonseGuard(전세가드)</b>는 전세 계약서를 기반으로 전세사기를 사전에 예방하기 위해 만들어진 인공지능 서비스입니다. 전세 계약서에 담긴 내용을 자동으로 분석하고, 공공 데이터를 기준으로 위험 요소를 확인함으로써, 사용자 스스로 계약의 신뢰도를 판단할 수 있도록 돕는 것이 목표입니다.

계약서를 작성하는 과정에서 가장 중요한 것은, 그 문서가 실제 부동산 정보와 일치하는지를 검증하는 일입니다. 하지만 일반 사용자 입장에서 계약서에 적힌 지번, 면적, 보증금 등의 정보가 사실인지 확인하는 것은 매우 어렵습니다. 전세사기 피해자 대부분은 계약서를 자세히 읽지 않아서가 아니라, **'의심조차 하지 못했기 때문에'** 피해를 입었습니다. 그만큼 정보에 접근하기 어렵고, 확인 절차가 사용자 친화적이지 않았다는 뜻입니다.

전세가드는 이 문제를 기술적으로 해결하고자 노력했습니다. 사용자가 계약서를 업로드하면, OCR 기술을 활용해 주소, 면적, 보증금 등의 정보를 자동으로 추출합니다. 추출된 정보는 내부에 저장된 실거래가 데이터와 비교되며, 건축물 정보는 **국토교통부 건축물대장 API**를 통해 검증됩니다. 계약서의 내용과 실제 건축물 정보 간의 일치 여부를 자동으로 확인할 수 있으며, 건물의 구조, 용도, 면적 등은 사기 여부를 판단하는 데 핵심적인 기준이 됩니다. 분석 결과는 단순한 경고 메시지가 아닌, 계약서를 기반으로 생성된 분석 결과 문서로 제공되어 사용자가 이를 근거로 판단하고 대응할 수 있도록 지원합니다.

우리는 누구나 집을 구하는 과정에서 불안함을 느끼지 않아도 되는 사회를 꿈꿉니다. 전문가가 아니더라도, 복잡한 법률 문서와 행정 데이터를 손쉽게 이해하고, 스스로 의심하고 검토할 수 있는 도구를 제공하고 싶었습니다. 전세가드는 단지 기술을 적용한 서비스가 아니라, **누군가의 삶을 지켜내는 데 실질적인 역할을 하는 도구**가 되기를 희망합니다. 우리의 작은 시도가, 한 사람의 큰 피해를 막을 수 있기를 진심으로 희망합니다.
</div>

## <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Animals/T-Rex.png" alt="T-Rex" width="27" height="27" /> 기술 스택

### 프론트엔드
![image](https://github.com/user-attachments/assets/56165466-e1a2-4a21-91b6-eb1b7cdd43c8)

### AI
![image](https://github.com/user-attachments/assets/116b76b0-7664-4a43-b008-159957719639)

### 백엔드
![image](https://github.com/user-attachments/assets/bdbf927e-c3cc-4f24-a9aa-32b72a3772b5)

### 인프라
![image](https://github.com/user-attachments/assets/06742ad3-1c17-4be0-bb22-dd09fb9880ab)

## <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Fire.png" alt="Fire" width="25" height="25" /> 아키텍처
![image](https://github.com/user-attachments/assets/9b312dee-03d7-4b5e-97b0-e4a8dd5320fa)

## <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Microsoft-Teams-Animated-Emojis/master/Emojis/Smilies/Blue%20Heart.png" alt="Blue Heart" width="25" height="25" /> ERD
![image](https://github.com/user-attachments/assets/2bb937e3-61ff-46f6-8690-5d3055ca1ef4)

## <img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Travel%20and%20places/Star.png" alt="Star" width="25" height="25" /> 팀원
|[김수진](https://github.com/sujeengim)|[이고은](https://github.com/g00u)|[성대열](https://github.com/Daeye0l)|[최민우](https://github.com/chaiminwoo0223)|
|:---:|:---:|:---:|:---:|
|<img src="https://github.com/sujeengim.png" width=400px>|<img src="https://github.com/g00u.png" width=400px>|<img src="https://github.com/Daeye0l.png" width=400px>|<img src="https://github.com/chaiminwoo0223.png" width=400px>|
|Frontend|Frontend|AI|Backend|
