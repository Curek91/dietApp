V_BOX_MANAGE=$1
MACHINE_NAME=$2

"$V_BOX_MANAGE\\VBoxManage.exe"  modifyvm $MACHINE_NAME --natpf1 "db,tcp,,5432,,5432"
"$V_BOX_MANAGE\\VBoxManage.exe"  modifyvm $MACHINE_NAME --natpf1 "http,tcp,,8080,,8080"
"$V_BOX_MANAGE\\VBoxManage.exe"  modifyvm $MACHINE_NAME --natpf1 "keycloak,tcp,,8180,,8180"
