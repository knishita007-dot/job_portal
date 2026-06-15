const express = require('express');
const router = express.Router();
const searchService = require('../service/searchService');

router.get('/:jobId', async (req, res) => res.json(await searchService.getDescription(req.params.jobId)));
router.post('/', async (req, res) => res.json(await searchService.createDescription(req.body)));
router.put('/:jobId', async (req, res) => res.json(await searchService.updateDescription(req.params.jobId, req.body)));

module.exports = router;
