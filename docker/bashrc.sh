#get docker-compose and store in persistent location
sudo curl -L "https://github.com/docker/compose/releases/download/1.25.4/docker-compose-$(uname -s)-$(uname -m)" -o /var/lib/boot2docker/docker-compose
sudo chmod +x /var/lib/boot2docker/docker-compose

#time zone settings ref https://stackoverflow.com/posts/51611246/revisions
cd /var/lib/boot2docker
tce-fetch.sh tzdata.tcz
mkdir ext
sudo mount tzdata.tcz ext -t squashfs -o loop,ro,bs=4096
cp ext/usr/local/share/zoneinfo/Europe/Warsaw ./Europe-Warsaw.tz
sudo umount ext
rm -rf ext tzdata.tcz

#create script bootlocal.sh
echo "#! /bin/bash" > /var/lib/boot2docker/bootlocal.sh
echo "cp /var/lib/boot2docker/Europe-Warsaw.tz /etc/localtime" >> /var/lib/boot2docker/bootlocal.sh
echo "cp /var/lib/boot2docker/docker-compose /usr/local/bin/docker-compose" >> /var/lib/boot2docker/bootlocal.sh
echo "#su -l docker -c \"tce-load -wi mc.tcz\"" >> /var/lib/boot2docker/bootlocal.sh

#add exec privilege to bootlocal.sh
chmod 777 /var/lib/boot2docker/bootlocal.sh