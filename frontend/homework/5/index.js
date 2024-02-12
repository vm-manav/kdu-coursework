const os = require('os');
const fs=require('fs');
const http = require('http');
const path = require('path');

function getSystemInformation(){
    const hostname=os.hostname();
    const operatingSystem=os.type();
    const architecture = os.arch();
    const osRelease = os.release();
    const uptime = os.uptime();
    const numberOfCPUCores = os.cpus().length;
    const totalMemory = os.totalmem();
    const freeMemory = os.freemem();
    const currentWorkingDirectory = process.cwd();

    return JSON.stringify({
        HostName: hostname,
        OperatingSystem: operatingSystem,
        Architecture: architecture,
        OSRelease: osRelease,
        Uptime: uptime,
        NumberOfCPUCores: numberOfCPUCores,
        TotalMemory: totalMemory,
        FreeMemory: freeMemory,
        CurrentWorkingDirectory: currentWorkingDirectory,
   });
}
function saveSystemInformationToFile(){
    fs.writeFileSync('systemInformation.json',getSystemInformation())  ;
}


const server = http.createServer((request,response) => {
    if (request.url === '/get-system-data') {
        saveSystemInformationToFile();
        fs.readFile('systemInformation.json','utf8',(err,systemInformation)=>{
            if(err){
                response.writeHead(500,{'Content-Type':'text'});
                response.end('Internal Server Error');
            }else{
                response.writeHead(200,{'Content-Type':'text' });
                response.write('Hello, my name is Manav Verma!\n');
                response.write('Here is my system information:\n');
                response.end(systemInformation);
            }
        });
    } else {
        response.writeHead(404, { 'Content-Type': 'text/plain' });
        response.end('Not Found');
    }
});

const PORT = 5000;
server.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});




function extractFileUrl(filePath) {
    const extension = path.extname(filePath);
    const baseName = path.basename(filePath);
    const directory = path.dirname(filePath);
    return { extension: extension, baseName: baseName,directory: directory,};
}

function extractFilePathInformation(filePaths) {
    const pathInformationArray = [];

    for (const filePath of filePaths) {
        const filePathInformation = extractFileUrl(filePath);
        pathInformationArray.push(filePathInformation);
    }
    return pathInformationArray;
}

const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf',
];

const filePathsInformation= extractFilePathInformation(filePaths);
console.log(filePathsInformation);

