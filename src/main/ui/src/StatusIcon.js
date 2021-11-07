import { ArrowDownward, ArrowForward, HourglassFull, Power, PowerOff } from "@mui/icons-material";
import { Tooltip } from "@mui/material";
import React from "react";

const StatusIcon = ({status}) => {
    const getIcon = (status) => {
        switch (status) {
            case 'RUNNING':
                return <Power sx={{color: 'green'}} fontSize="large"/>;
            case 'STOPPED':
                return <PowerOff sx={{color: 'red'}} fontSize="large"/>;
            case 'PENDING':
                return <><PowerOff sx={{color: 'red'}} fontSize="large"/> <ArrowDownward/> <Power sx={{color: 'green'}} fontSize="large"/></>;
            case 'STOPPING':
                return <><Power sx={{color: 'green'}} fontSize="large"/> <ArrowDownward/> <PowerOff sx={{color: 'red'}} fontSize="large"/></>;
            default:
                return <HourglassFull fontSize="large"/>;
        }
    };

    return (
        <Tooltip title={status} placement="top">
            {getIcon(status)}
        </Tooltip>
    );
};

export default StatusIcon;