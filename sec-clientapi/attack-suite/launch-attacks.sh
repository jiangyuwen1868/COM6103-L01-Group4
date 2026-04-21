#!/bin/bash

# Configuration
JAR_NAME="cspClientAPI-1.0.0-with-dependencies.jar"
LOG_DIR="./logs"
LOG_FILE="${LOG_DIR}/attack-$(date +%Y%m%d).log"

# Create log directory
mkdir -p ${LOG_DIR}

# Check jar exists
if [ ! -f "${JAR_NAME}" ]; then
    echo "ERROR: Jar file ${JAR_NAME} not found!"
    exit 1
fi

# Check java command
if ! command -v java &> /dev/null; then
    echo "ERROR: java command not found, please install JDK first!"
    exit 1
fi

# Help info
usage() {
    echo "============================================="
    echo "Attack Runner Usage:"
    echo "  sh $0 replay      Basic Replay Attack"
    echo "  sh $0 replay_ts   Replay Attack With Forged Timestamp"
    echo "  sh $0 replay_ca   Replay Attack With Forged CASignature"
    echo "  sh $0 tampering   Data Tampering Attack"
    echo "  sh $0 mitm        MITM Attack"
    echo "  sh $0 dos         DoS Attack"
    echo "  sh $0 dict        Dictionary-Based Brute-Force Attack"
    echo "  sh $0 help        Show help message"
    echo "============================================="
}

# Log function
log() {
    echo "[$(date '+%Y-%m-%d %H:%M:%S')] $1" | tee -a ${LOG_FILE}
}

# Check parameters
if [ $# -ne 1 ]; then
    echo "ERROR: Please input one attack mode!"
    usage
    exit 1
fi

MODE=$1

# Run attack
case "${MODE}" in
    replay|replay_ts|replay_ca|tampering|mitm|dos|dict)
        log "Start running attack mode: ${MODE}"
        java -jar ${JAR_NAME} ${MODE} 2>&1 | tee -a ${LOG_FILE}
        log "Attack mode ${MODE} finished"
        log "------------------------------------------------------------"
        ;;
    help)
        usage
        ;;
    *)
        echo "ERROR: Invalid mode: ${MODE}"
        usage
        exit 1
        ;;
esac

exit 0

