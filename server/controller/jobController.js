const express = require('express');
const router = express.Router();
const jobService = require('../service/jobService');

router.get('/', async (req, res) => res.json(await jobService.getAllJobs()));
router.post('/', async (req, res) => res.json(await jobService.createJob(req.body)));
router.get('/type/:type', async (req, res) => res.json(await jobService.getJobsByType(req.params.type)));
router.get('/location/:location', async (req, res) => res.json(await jobService.getJobsByLocation(req.params.location)));
router.get('/user/:userId', async (req, res) => res.json(await jobService.getJobsByUser(req.params.userId)));
router.get('/search', async (req, res) => res.json(await jobService.searchJobs(req.query.keyword)));
router.get('/:id', async (req, res) => res.json(await jobService.getJobById(req.params.id)));
router.put('/:id', async (req, res) => res.json(await jobService.updateJob(req.params.id, req.body)));
router.delete('/:id', async (req, res) => res.json(await jobService.deleteJob(req.params.id)));

module.exports = router;
