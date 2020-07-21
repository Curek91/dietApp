DRIVER=virtualbox
DISK_SIZE=5000
ALLOCATED_MEMORY=2048
CPU_COUNT=1

SHARED_FOLDER="$(pwd)"
echo "Shared folder: $SHARED_FOLDER"
SHARED_FOLDER=${SHARED_FOLDER:1:1}:${SHARED_FOLDER:2}
echo "Shared folder: $SHARED_FOLDER"
SHARED_FOLDER=${SHARED_FOLDER//["//"]/"\\"}
echo "Shared folder: $SHARED_FOLDER"

MOUNTED_DIRECTORY=home/docker/docker
MACHINE_NAME=dietAppDocker
V_BOX_MANAGE=$VBOX_MSI_INSTALL_PATH

function myscp() {
	src=bashrc.sh
	dst=/home/docker
	sshport=$(docker-machine inspect --format {{.Driver.SSHPort}} ${MACHINE_NAME})
	sshkeypath=$(docker-machine inspect --format {{.Driver.SSHKeyPath}} ${MACHINE_NAME})
	
	scp -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -o LogLevel=quiet -3 -o IdentitiesOnly=yes -o Port=${sshport} -o IdentityFile="${sshkeypath}" "${src}" "docker@127.0.0.1:${dst}"
}

echo "Creating VM $MACHINE_NAME..."
echo "Shared folder: $SHARED_FOLDER"
echo "MOUNTED_DIRECTORY: $MOUNTED_DIRECTORY"
docker-machine create --driver $DRIVER --virtualbox-disk-size $DISK_SIZE --virtualbox-memory $ALLOCATED_MEMORY --virtualbox-cpu-count $CPU_COUNT --virtualbox-share-folder "${SHARED_FOLDER}:${MOUNTED_DIRECTORY}" $MACHINE_NAME

echo "Applying config..."
myscp
#docker-machine ssh $MACHINE_NAME "ARTIFACTORY_ADDRESS=$ARTIFACTORY_ADDRESS USERNAME=$USERNAME PASSWORD=$PASSWORD >> /var/lib/boot2docker/bootlocal.sh"
docker-machine ssh $MACHINE_NAME "chmod 777 /home/docker/bashrc.sh"
docker-machine ssh $MACHINE_NAME "./bashrc.sh"
docker-machine stop $MACHINE_NAME
#Final config of VM
echo "VM configuration ..."
./vmconfig.sh "$V_BOX_MANAGE" $MACHINE_NAME
docker-machine start $MACHINE_NAME

echo "=========================="
echo "DOCKER-MACHINE IS READY. SSH AT PORT $(docker-machine inspect --format {{.Driver.SSHPort}} ${MACHINE_NAME})"
echo "User: docker"
echo "Password: tcuser"